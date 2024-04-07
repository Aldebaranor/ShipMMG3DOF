package song.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WindForce {
    private double Xwd;
    private double Ywd;
    private double Nwd;
    private double winDirnEncDeg;
    private double winSpdEnc;
    private double winThetaEnc;
}
