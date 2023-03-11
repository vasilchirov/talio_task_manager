package commons;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

@Entity
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long listId;
    public String title;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    public Board board;

    @OneToMany(mappedBy = "list")
    public Set<Task> tasks;

    /**
     * Creates a new TaskList object with the given title, board and an empty set of Tasks.
     *
     * @param title The title to be given to the TaskList
     * @param board The Board on which this TaskList will be
     */
    public TaskList(String title, Board board) {
        this.title = title;
        this.board = board;
        tasks = new HashSet<>();
    }

    @SuppressWarnings("unused")
    private TaskList() {
        // For object mapper
    }

    /**
     * Compares this object to another object and returns true only if they are equal.
     * Two TaskLists are equal if they have the same listId, title, set of tasks
     * and are on the same Board.
     *
     * @param o The object that is going to be compared to this
     * @return True if and only if the other object is a TaskList with the same attributes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return listId == taskList.listId && Objects.equals(title, taskList.title)
                && Objects.equals(board.boardId, taskList.board.boardId)
                && Objects.equals(tasks, taskList.tasks);
    }

    /**
     * Generates a hash code for this object,
     * which uses the listId, title, board and tasks attributes.
     *
     * @return The generated hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(listId, title, board.boardId, tasks);
    }

    /**
     * Adds a Task to the set of all Tasks in this TaskList.
     *
     * @param task The Task to be added to this TaskList
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Generates a String representation of this object and its attributes in a multi-line style.
     *
     * @return The generated String representation for this object
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
    }
}
