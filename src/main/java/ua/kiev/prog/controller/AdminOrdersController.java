package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Order;
import ua.kiev.prog.entity.OrderStatus;
import ua.kiev.prog.entity.POJO.DeleteOrder;
import ua.kiev.prog.entity.POJO.DeleteProductModel;
import ua.kiev.prog.entity.POJO.OrderData;
import ua.kiev.prog.exception.OrderNotFoundException;
import ua.kiev.prog.service.OrderService;
import ua.kiev.prog.service.UserService;

import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminOrdersController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public String adminIndex(Model model) {
        String login = "";
        String role = "";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            if (userDetail != null) {
                login = userDetail.getUsername();

                CustomUser dbUser = userService.findByLogin(login);
                model.addAttribute("login", login);
                role = dbUser.getRole().toString();
                model.addAttribute("role", role);
            } else {
                login = "";
            }
        }
        return "adminIndex";
    }

    @GetMapping("/orders")
    public String ordersList(Model model,
                             @RequestParam(value = "sortBy", required = false) String sortBy,
                             @RequestParam(value = "order", required = false) String order,
                             @RequestParam(value = "page", required = false) String page,
                             @RequestParam(value = "size", required = false) String size,
                             @RequestParam(value = "searchId", required = false) String searchId,
                             @RequestParam(value = "searchName", required = false) String searchName,
                             @RequestParam(value = "searchEmail", required = false) String searchEmail) {
        int ipage = (page == null) ? 0 : Integer.parseInt(page) - 1;
        int isize = (size == null) ? 10 : Integer.parseInt(size);
        Sort.Direction direction = (order == null) ? Sort.Direction.DESC : ((order.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC);
        String sSortBy = (sortBy == null) ? "id" : sortBy;
        if (sSortBy.equals("name"))
            sSortBy = "firstName";
        else if (sSortBy.equals("total"))
            sSortBy = "total";
        else
            sSortBy = "id";

        Pageable pagingSort = PageRequest.of(ipage, isize, direction, sSortBy);
        System.out.println(pagingSort);

        Page<Order> orderList;
        Long lSearchId = 0L;
        try {
            lSearchId = (searchId == null) ? 0L : Long.parseLong(searchId);
        } catch (NumberFormatException e) {
            lSearchId = 0L;
            searchId = "";
        }
        searchName = (searchName == null) ? "" : searchName;
        searchEmail = (searchEmail == null) ? "" : searchEmail;
        System.out.println("-------------");
        System.out.println(searchId + " -- " + pagingSort);
        if (lSearchId > 0 && (searchName.length() == 0 && searchEmail.length() == 0)) {
            orderList = orderService.findByOrderId(lSearchId, pagingSort);
        } else if (searchName.length() > 0 && (searchEmail.length() == 0 && lSearchId == 0L)) {
            orderList = orderService.findByName(searchName, pagingSort);
        } else if (searchEmail.length() > 0 && (searchName.length() == 0 && lSearchId == 0L)) {
            orderList = orderService.findByEmail(searchEmail, pagingSort);
        } else if (lSearchId > 0 && searchName.length() > 0 && searchEmail.length() == 0) {
            orderList = orderService.findByOrderIdAndName(lSearchId, searchName, pagingSort);
        } else if (lSearchId > 0 && searchEmail.length() > 0 && searchEmail.length() == 0) {
            orderList = orderService.findByOrderIdAndEmail(lSearchId, searchEmail, pagingSort);
        } else if (lSearchId == 0L && searchName.length() > 0 && searchEmail.length() > 0) {
            orderList = orderService.findByNameAndEmail(searchName, searchEmail, pagingSort);
        } else if (lSearchId > 0 && searchName.length() > 0 && searchEmail.length() > 0) {
            orderList = orderService.findByOrderIdAndNameAndEmail(lSearchId, searchName, searchEmail, pagingSort);
        } else {
            orderList = orderService.getAllOrders(pagingSort);
        }

        model.addAttribute("orders", orderList.getContent());
        model.addAttribute("endpage", orderList.getTotalPages());
        model.addAttribute("startpage", 1);
        model.addAttribute("currentPage", orderList.getNumber() + 1);

        model.addAttribute("searchId", searchId);
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchEmail", searchEmail);

        return "orders";
    }

    @GetMapping("/orders/view/{id}")
    public String ordersList(Model model,
                             @PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.findOrderById(id);

        model.addAttribute("order", order);
        model.addAttribute("orderStatuses", OrderStatus.values());
        return "order_view";

    }

    @PostMapping("/orders/changeStatus")
    public ResponseEntity<?> updateOrderStatus(@RequestBody OrderData orderData) {
        Order order = orderService.findOrderById(orderData.getId());

        OrderStatus status = OrderStatus.valueOf(orderData.getStatus());
        order.setStatus(status);
        orderService.saveOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        String message = "{\"message\" : \"Order status successfully updated.\"}";
        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/orders/delete")
    public ResponseEntity<?> deleteOrder(@RequestBody DeleteOrder deleteOrder) {
        Order order = orderService.findOrderById(deleteOrder.getId());
        orderService.deleteOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        String message = "{\"message\" : \"Order successfully Deleted!\"}";
        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }

    @GetMapping("/orders/user/{id}")
    public String ordersList(Model model,
                             @RequestParam(value = "sortBy", required = false) String sortBy,
                             @RequestParam(value = "order", required = false) String order,
                             @RequestParam(value = "page", required = false) String page,
                             @RequestParam(value = "size", required = false) String size,
                             @PathVariable Long id) {
        int ipage = (page == null) ? 0 : Integer.parseInt(page) - 1;
        int isize = (size == null) ? 10 : Integer.parseInt(size);
        Sort.Direction direction = (order == null) ? Sort.Direction.DESC : ((order.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC);
        String sSortBy = (sortBy == null) ? "id" : sortBy;
        if (sSortBy.equals("name"))
            sSortBy = "firstName";
        else if (sSortBy.equals("total"))
            sSortBy = "total";
        else
            sSortBy = "id";

        Pageable pagingSort = PageRequest.of(ipage, isize, direction, sSortBy);


        Page<Order> orderList;
        orderList = orderService.findByUserId(id, pagingSort);


        model.addAttribute("orders", orderList.getContent());
        model.addAttribute("endpage", orderList.getTotalPages());
        model.addAttribute("startpage", 1);
        model.addAttribute("currentPage", orderList.getNumber() + 1);

        model.addAttribute("userId", id);


        return "orders";
    }

}
