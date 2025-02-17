import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int lastId = 0;  // 静态变量，用于跟踪最后分配的ID
    private int id;
    private String description; // 任务描述
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 用于序列化/反序列化日期的DateTimeFormatter
    // 通过DateTimeFormatter.ISO_LOCAL_DATE_TIME获取一个标准的日期格式化对象
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // 构造函数
    public Task(String description) {
        this.id = ++lastId; // 通过递增lastId来分配唯一的ID，变相实现了自增ID
        this.description = description;
        this.status = Status.TODO; // 默认状态为待办
        this.createdAt = LocalDateTime.now(); // 创建时间为当前时间，通过LocalDateTime.now()获取
        this.updatedAt = LocalDateTime.now(); // 更新时间为当前时间
    }

    public int getId() {
        return id;
    }

    // 将任务标记为进行中，并且同步更新updatedAt字段
    public void markInProgress() {
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    // 将任务标记为已完成，并且同步更新updatedAt字段
    public void markDone() {
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    // 更新任务描述，并且同步更新updatedAt字段
    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    // 将任务转换为JSON格式的字符串
    // formatter是一个DateTimeFormatter对象，用于格式化日期，将LocalDateTime对象转换为字符串
    public String toJson() {
        return "{\"id\":\"" + id + "\", " +
                "\"description\":\"" + description.strip() + "\", " +
                "\"status\":\"" + status.toString() + "\", " +
                "\"createdAt\":\"" + createdAt.format(formatter) + "\", " +
                "\"updatedAt\":\"" + updatedAt.format(formatter) + "\"}";
    }

    // 从JSON格式的字符串中解析任务对象
    public static Task fromJson(String json) {
        // 去掉字符串中的大括号和引号，然后用逗号分隔，得到一个字符串数组
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] json1 = json.split(",");

        // 从字符串数组中提取任务的各个字段
        // 通过split方法和正则表达式提取字段，移除首尾空格，得到第二个元素，也就是值
        // 如id：1，通过split方法和正则表达式提取id字段
        String id = json1[0].split(":")[1].strip();
        String description = json1[1].split(":")[1].strip();
        String statusString = json1[2].split(":")[1].strip();
        // 这里使用的是a-Z作为分隔符，因为iso8601格式的日期中包含了字母T，作为时间和日期的分隔符
        String createdAtStr = json1[3].split("[a-z]:")[1].strip();
        String updatedAtStr = json1[4].split("[a-z]:")[1].strip();

        // 上面获取的是状态的字符串，需要将字符串转换为对应的枚举类型
        // 转换成大写字母，然后替换空格为下划线，再通过valueOf方法转换为枚举类型
        Status status = Status.valueOf(statusString.toUpperCase().replace(" ", "_"));

        // 创建一个Task对象，并设置各个字段的值
        Task task = new Task(description);
        task.id = Integer.parseInt(id);
        task.status = status;
        task.createdAt = LocalDateTime.parse(createdAtStr, formatter); // 使用parese方法将根据formatter格式解析的字符串转换为LocalDateTime对象
        task.updatedAt = LocalDateTime.parse(updatedAtStr, formatter);

        // 更新lastId，确保分配的ID是唯一的，如果当前任务的ID大于lastId，就将lastId设置为当前任务的ID
        if (Integer.parseInt(id) > lastId) {
            lastId = Integer.parseInt(id);
        }

        return task;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "id: " + id + ", description: " + description.strip() + ", status: " + status.toString() +
                ", createdAt: " + createdAt.format(formatter) + ", updatedAt: " + updatedAt.format(formatter);
    }
}
