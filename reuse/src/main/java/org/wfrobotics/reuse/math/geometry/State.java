package frc.reuse.math.geometry;

import frc.reuse.math.CSVWritable;
import frc.reuse.math.interpolation.Interpolable;

public interface State<S> extends Interpolable<S>, CSVWritable {
    double distance(final S other);

    boolean equals(final Object other);

    String toString();

    String toCSV();
}
