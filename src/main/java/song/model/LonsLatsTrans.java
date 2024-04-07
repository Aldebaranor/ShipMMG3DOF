package song.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LonsLatsTrans {

    public List<double[]> ptsMDesiredArray;

    public double[] origin;

    public List<double[]> ptsRelaMDesiredArray;

}
