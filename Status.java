public enum Status {
    // 通过枚举类型定义任务的状态
    TODO("Todo"), // 待办
    IN_PROGRESS("In progress"), // 进行中
    DONE("Done"); // 已完成

    private final String value;

    Status(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
