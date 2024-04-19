package vn.iback.studentmanager.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("tài khoản đã tồn tại");
    }
}

