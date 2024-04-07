package song.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RongeKuta {
    private double uNext;
    private double vNext;
    private double rNext;
    private double xNext;
    private double yNext;
    private double faiDegNext;
    private double headingDegNext;
    private double piaoJiaoRad;
    private double uAcc;
    private double vAcc;
    private double rAccRad;

}
