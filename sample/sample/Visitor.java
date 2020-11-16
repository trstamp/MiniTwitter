package sample;

public interface Visitor {
    // number of methods = number of Visitable classes
    public void visitUser(User user);

    public void visitUserGroup(UserGroup group);
}
