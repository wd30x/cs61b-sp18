package byog.Core;

import java.io.Serializable;

public class Position implements Serializable {
    int x;
    int y;
    private static final long serialVersionUID = 123123123123124L;

    public Position(int X, int Y) {
        x = X;
        y = Y;
    }
}
