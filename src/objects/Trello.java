package objects;

import java.util.List;

public class Trello {

    List<Member> members;
    List<Task> tasks;
    List<Flag> flags;

    Trello() {

    }

    /*


     */

    public int addMember(Member member) {
        members.add(member);
        return 1;
    }

    public int deleteMember(String name) {
        return 0;
    }

    /*


     */

    public int addTask(Task task) {
        tasks.add(task);
        return 1;
    }

    public int deleteTask(String name) {
        return 0;
    }

    /*


     */

    public int addFlag(Flag flag) {
        flags.add(flag);
        return 1;
    }

    public int deleteFlag(String name) {
        return 0;
    }

}
