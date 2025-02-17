# 任务命令行应用

这是一个简单的命令行界面（CLI）应用程序，用于管理任务。您可以直接从终端添加、更新、删除、标记和列出任务。

题目来自roadmap的[任务跟踪器 CLI](https://roadmap.sh/projects/task-tracker)项目，实现代码来自https://github.com/lephuocloc1729

在学习的基础上添加了注释

## 特性

- **添加任务：**添加一个带有描述的新任务。

- **更新任务：**更新已有任务的描述信息。

- **删除任务：**根据任务ID删除任务。

- **标记任务：**将任务标记为“进行中”或“完成”。

- **列出任务：**列出所有任务或按状态（例如，待办事项、进行中、完成）对它们进行筛选。

## 安装

1. **克隆存储库：**

   出处：

   ```bash
   git clone https://github.com/lephuocloc1729/task_tracker_cli
   ```

   添加注释后：

   ```bash
   
   ```

   

2. **编译源代码：**

   ```bash
   javac TaskCLIApp.java Task.java TaskManager.java Status.java
   ```

   因为编码问题可以使用：

   ```bash
   javac -encoding UTF-8 TaskCLIApp.java Task.java TaskManager.java Status.java
   ```

   将编译后的字节码文件存放在另一个文件target可以使用：

   ```bash
    javac -encoding UTF-8 -d target TaskCLIApp.java Task.java TaskManager.java Status.java
   ```

3. **运行应用程序：**

   ```bash
   java TaskCLIApp <command> [arguments]
   ```

## 用法

```bash
# 添加任务
java TaskCLIApp add "Buy groceries"
# 输出: Task added successfully (ID: 1)

# 更新任务
java TaskCLIApp update 1 "Buy groceries and cook dinner"
# 输出: Task updated successfully (ID: 1)

# 删除任务
java TaskCLIApp delete 1
# 输出: Task deleted successfully (ID: 1)

# 将任务状态修改为进行中
java TaskCLIApp mark-in-progress 1
# 删除: Task marked as in progress (ID: 1)

# 将任务状态修改为完成
java TaskCLIApp mark-done 1
# 删除: Task marked as done (ID: 1)

# 列出所有任务
java TaskCLIApp list
# 删除: List of all tasks

# 列出指定状态的任务
java TaskCLIApp list todo
java TaskCLIApp list in-progress
java TaskCLIApp list done
```



# Task CLI Application

This is a simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal.

## Features

- **Add a Task:** Add a new task with a description.
- **Update a Task:** Update the description of an existing task.
- **Delete a Task:** Remove a task by its ID.
- **Mark a Task:** Mark a task as "in progress" or "done."
- **List Tasks:** List all tasks or filter them by status (e.g., `todo`, `in progress`, `done`).

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/lephuocloc1729/task_tracker_cli
   cd task_tracker_cli

2. **Compile the source code:**
   
   ```bash
   javac TaskCLIApp.java Task.java TaskManager.java Status.java
3. **Run the application:**
   
   ```bash
   java TaskCLIApp <command> [arguments]
   ```
## Usage
```bash
# Adding a new task
java TaskCLIApp add "Buy groceries"
# 删除: Task added successfully (ID: 1)

# Updating a task
java TaskCLIApp update 1 "Buy groceries and cook dinner"
# 删除: Task updated successfully (ID: 1)

# Deleting a task
java TaskCLIApp delete 1
# 删除: Task deleted successfully (ID: 1)

# Marking a task as in progress
java TaskCLIApp mark-in-progress 1
# 删除: Task marked as in progress (ID: 1)

# Marking a task as done
java TaskCLIApp mark-done 1
# 删除: Task marked as done (ID: 1)

# Listing all tasks
java TaskCLIApp list
# 删除: List of all tasks

# Listing tasks by status
java TaskCLIApp list todo
java TaskCLIApp list in-progress
java TaskCLIApp list done

```