package vn.iback.studentmanager.exception;

public class BaiVietAlreadyExistsException extends RuntimeException{
    public BaiVietAlreadyExistsException() {
        super("BaiViet đã tồn tại");
    }
}
