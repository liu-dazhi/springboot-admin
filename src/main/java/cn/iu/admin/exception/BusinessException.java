package cn.iu.admin.exception;

public class BusinessException extends RuntimeException {

    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public static void throwExceptionWithNull(Object value, String name) {
        if (value == null) {
            throw new BusinessException(String.format("操作失败，原因：%s", name));
        }
    }
}
