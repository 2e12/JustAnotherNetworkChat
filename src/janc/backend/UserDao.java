package janc.backend;

enum loginstate { //Thus enum is required for checking later the login state
    correct,
    worngpassword,
    nouser
}

public interface UserDao {
    public abstract void insertUser(User user);
    public abstract loginstate checkLoginCredentials(User user);
}
