import java.util.List;

public interface IWinDetector {

    List<TokenPlace> getConnecting4();
    boolean AddToken(TokenPlace newTokenPlace);

}
