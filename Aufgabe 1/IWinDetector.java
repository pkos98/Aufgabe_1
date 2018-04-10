import java.util.List;

public interface IWinDetector {

    List<Token> getConnecting4();
    boolean AddToken(Token newToken);

}
