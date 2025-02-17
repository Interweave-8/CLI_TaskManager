import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    // 通过Path.of()方法创建一个Path对象，指向tasks.json文件，该文件在当前工作目录下
    private final Path FILE_PATH = Path.of("tasks.json");
    private List<Task> tasks; // 用于存储任务列表


    public TaskManager(){
        this.tasks = loadTasks(); // 从tasks.json文件中读取任务列表
    }

    // 读取tasks.json文件中的任务列表
    private List<Task> loadTasks(){
        List<Task> stored_tasks = new ArrayList<>(); // 用于存储从文件中读取的任务列表

        // 如果文件不存在，则返回一个空列表
        if (!Files.exists(FILE_PATH)){
            return new ArrayList<>();
        }

        try {
            // 以字符串形式读取文件内容，存储在jsonContent中
            String jsonContent = Files.readString(FILE_PATH);
            // 将jsonContent字符串中的方括号去掉，然后用逗号分隔，得到一个字符串数组，每个元素是一个任务的JSON字符串
            // 但是由于最后一个任务的JSON字符串后面没有逗号，所以需要特殊处理
            String[] taskList = jsonContent.replace("[", "")
                                            .replace("]", "")
                                            .split("},");
            // 遍历任务列表，将每个JSON字符串转换为Task对象，然后添加到stored_tasks列表中
            for (String taskJson : taskList){
                // 如果JSON字符串不是以大括号结尾，则在字符串末尾添加一个大括号
                // 因为split方法会将大括号和逗号去掉，除了最后一个任务的JSON字符串保留了大括号，其他任务的JSON字符串都会缺少大括号，需要手动添加
                if (!taskJson.endsWith("}")){
                    taskJson = taskJson + "}";
                    stored_tasks.add(Task.fromJson(taskJson));
                } else {
                    stored_tasks.add(Task.fromJson(taskJson)); // 如果JSON字符串以大括号结尾，则直接转换为Task对象
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return stored_tasks;
    }

    // 将任务列表保存到tasks.json文件中
    public void saveTasks(){
        StringBuilder sb = new StringBuilder(); // 拼接JSON字符串的StringBuilder对象
        sb.append("[\n"); // 在字符串sb的开头添加一个方括号，表示一个JSON数组的开始
        for (int i = 0; i < tasks.size(); i++){
            sb.append(tasks.get(i).toJson()); // 使用Task对象的toJson方法将Task对象转换为JSON字符串，然后添加到字符串sb中
            if (i < tasks.size() - 1){ // 如果不是最后一个任务，则在字符串sb的末尾添加一个逗号
                sb.append(",\n");
            }
        }
        sb.append("\n]"); // 在字符串sb的末尾添加一个方括号，表示一个JSON数组的结束

        String jsonContent = sb.toString(); // 将StringBuilder对象转换为字符串
        try {
            Files.writeString(FILE_PATH, jsonContent); // 将JSON字符串写入tasks.json文件
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // 添加任务
    public void addTask(String description){
        Task new_task = new Task(description); // 创建一个新的Task对象，传入任务描述参数
        tasks.add(new_task); // 将新的Task对象添加到任务列表中
        System.out.println("Task added successfully (ID: " + new_task.getId() + ")"); // 打印添加成功的提示信息
    }

    // 更新任务
    public void updateTask(String id, String new_description){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        task.updateDescription(new_description);
    }

    // 删除任务
    public void deleteTask(String id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        tasks.remove(task);
    }

    // 将任务标记为进行中
    public void markInProgress(String id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        task.markInProgress();
    }

    // 将任务标记为已完成
    public void markDone(String id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        task.markDone();
    }

    // 列出任务
    public void listTasks(String type){
        for (Task task : tasks){
            String status = task.getStatus().toString().strip();
            if (type.equals("All") || status.equals(type)){
                System.out.println(task.toString());
            }
        }
    }

    // 根据ID查找任务
    public Optional<Task> findTask(String id) {
        return tasks.stream().filter((task) -> task.getId() == Integer.parseInt(id)).findFirst();
    }


}
