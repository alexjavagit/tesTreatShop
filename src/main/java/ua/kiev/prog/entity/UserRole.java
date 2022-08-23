package ua.kiev.prog.entity;

public enum UserRole {
    ADMIN, USER;

    @Override
    public String toString() {
        return name();
    }
}
