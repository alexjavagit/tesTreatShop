<jsp:include page="header.jsp"  flush="true"></jsp:include>

<div class="container">

    <h3 class="booking-title">Orders</h3>
    <form class="booking-item-dates-change mb30">
        <div class="row">
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>From</label>
                    <input class="typeahead form-control" value="Great Britan, London" placeholder="City, Hotel Name or U.S. Zip Code" type="text" />
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>To</label>
                    <input class="typeahead form-control" value="United States, New York" placeholder="City, Hotel Name or U.S. Zip Code" type="text" />
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group form-group-icon-left"><i class="fa fa-calendar input-icon input-icon-hightlight"></i>
                    <label>Departing</label>
                    <input class="date-pick form-control" data-date-format="MM d, D" type="text" />
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group form-group-select-plus">
                    <label>Passengers</label>
                    <div class="btn-group btn-group-select-num" data-toggle="buttons">
                        <label class="btn btn-primary active">
                            <input type="radio" name="options" />1</label>
                        <label class="btn btn-primary">
                            <input type="radio" name="options" />2</label>
                        <label class="btn btn-primary">
                            <input type="radio" name="options" />3</label>
                        <label class="btn btn-primary">
                            <input type="radio" name="options" />4</label>
                        <label class="btn btn-primary">
                            <input type="radio" name="options" />4+</label>
                    </div>
                    <select class="form-control hidden">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option selected="selected">5</option>
                        <option>6</option>
                        <option>7</option>
                        <option>8</option>
                        <option>9</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                        <option>13</option>
                        <option>14</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div class="row">

        <div class="col-md-9">
            <div class="nav-drop booking-sort">
                <h5 class="booking-sort-title"><a href="#">Sort: Sort: Price (low to high)<i class="fa fa-angle-down"></i><i class="fa fa-angle-up"></i></a></h5>
                <ul class="nav-drop-menu">
                    <li><a href="#">Price (high to low)</a>
                    </li>
                    <li><a href="#">Duration</a>
                    </li>
                    <li><a href="#">Stops</a>
                    </li>
                    <li><a href="#">Arrival</a>
                    </li>
                    <li><a href="#">Departure</a>
                    </li>
                </ul>
            </div>
            <ul class="booking-list">
                <c:if test="${empty orders}">
                    We have not found any order yet. Please try again.
                </c:if>
                <c:forEach items="${orders}" var="order">
                <li>
                    <div class="booking-item-container">
                        <div class="booking-item">
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="booking-item-airline-logo">
                                        <img src="img/american-airlines.jpg" alt="Image Alternative text" title="Image Title" />
                                        <p>American Airlines</p>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="booking-item-flight-details">
                                        <div class="booking-item-departure"><i class="fa fa-plane"></i>
                                            <h5>10:25 PM</h5>
                                            <p class="booking-item-date">Sun, Mar 22</p>
                                            <p class="booking-item-destination">London, England, United Kingdom (LHR)</p>
                                        </div>
                                        <div class="booking-item-arrival"><i class="fa fa-plane fa-flip-vertical"></i>
                                            <h5>12:25 PM</h5>
                                            <p class="booking-item-date">Sat, Mar 23</p>
                                            <p class="booking-item-destination">New York, NY, United States (JFK)</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <h5>22h 50m</h5>
                                    <p>non-stop</p>
                                </div>
                                <div class="col-md-3"><span class="booking-item-price">$447</span><span>/person</span>
                                    <p class="booking-item-flight-class">Class: Business</p><a class="btn btn-primary" href="#">Select</a>
                                </div>
                            </div>
                        </div>
                        <div class="booking-item-details">
                            <div class="row">
                                <div class="col-md-8">
                                    <p>Flight Details</p>
                                    <h5 class="list-title">London (LHR) to Charlotte (CLT)</h5>
                                    <ul class="list">
                                        <li>US Airways 731</li>
                                        <li>Economy / Coach Class ( M), AIRBUS INDUSTRIE A330-300</li>
                                        <li>Depart 09:55 Arrive 15:10</li>
                                        <li>Duration: 9h 15m</li>
                                    </ul>
                                    <h5>Stopover: Charlotte (CLT) 7h 1m</h5>
                                    <h5 class="list-title">Charlotte (CLT) to New York (JFK)</h5>
                                    <ul class="list">
                                        <li>US Airways 1873</li>
                                        <li>Economy / Coach Class ( M), Airbus A321</li>
                                        <li>Depart 22:11 Arrive 23:53</li>
                                        <li>Duration: 1h 42m</li>
                                    </ul>
                                    <p>Total trip time: 17h 58m</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                </c:forEach>

            </ul>
            <p class="text-right">Not what you're looking for? <a class="popup-text" href="#search-dialog" data-effect="mfp-zoom-out">Try your search again</a>
            </p>
        </div>
    </div>
    <div class="gap"></div>
</div>

<jsp:include page="footer.jsp"  flush="true"></jsp:include>