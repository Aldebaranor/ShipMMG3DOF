package song.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Input implements Serializable {
    private static final long serialVersionUID = 6019454420457031855L;
    double n;
    double derta;
    String pathFinal;
}
