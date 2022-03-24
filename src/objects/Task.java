package objects;

import java.util.List;

public class Task {

    public String name;
    public String descripion;
    public List<Flag> flags;
    public List<Member> members;

    Task(String name, String description) {
        this.name = name;
        this.descripion = description;
    }

    public int addMember(Member member) {
        members.add(member);
        return 1;
    }

    public int deleteMember(String name) {
        return 0;
    }

    public int addFlag(Flag flag) {
        flags.add(flag);
        return 1;
    }

    public int deleteFlag(String name) {
        return 0;
    }

}
