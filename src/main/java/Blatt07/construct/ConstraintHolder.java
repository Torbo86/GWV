package Blatt07.construct;

import java.util.ArrayList;
import java.util.List;

public class ConstraintHolder {

    public List<Constraint> getAllContstraintsForA1D1() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A1D1", 0, "A1D2", 1));
        constraintList.add(new Constraint("A1D1", 0, "A1D3", 2));
        constraintList.add(new Constraint("A1D1", 0, "A2D1", 1));
        constraintList.add(new Constraint("A1D1", 0, "A3D1", 2));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA2D1() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A2D1", 1, "A1D1", 0));
        constraintList.add(new Constraint("A2D1", 1, "A3D1", 2));
        constraintList.add(new Constraint("A2D1", 0, "A2D2", 1));
        constraintList.add(new Constraint("A2D1", 0, "A2D3", 2));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA3D1() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A3D1", 0, "A3D2", 1));
        constraintList.add(new Constraint("A3D1", 0, "A3D3", 2));
        constraintList.add(new Constraint("A3D1", 2, "A2D1", 1));
        constraintList.add(new Constraint("A3D1", 2, "A1D1", 0));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA1D2() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A1D2", 0, "A2D2", 1));
        constraintList.add(new Constraint("A1D2", 0, "A3D2", 2));
        constraintList.add(new Constraint("A1D2", 1, "A1D1", 0));
        constraintList.add(new Constraint("A1D2", 1, "A1D3", 2));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA2D2() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A2D2", 1, "A1D2", 0));
        constraintList.add(new Constraint("A2D2", 1, "A3D2", 2));
        constraintList.add(new Constraint("A2D2", 1, "A2D1", 0));
        constraintList.add(new Constraint("A2D2", 1, "A2D3", 2));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA3D2() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A3D2", 2, "A1D2", 0));
        constraintList.add(new Constraint("A3D2", 2, "A2D2", 1));
        constraintList.add(new Constraint("A3D2", 1, "A3D1", 0));
        constraintList.add(new Constraint("A3D2", 1, "A3D3", 2));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA1D3() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A1D3", 2, "A1D2", 1));
        constraintList.add(new Constraint("A1D3", 2, "A1D1", 0));
        constraintList.add(new Constraint("A1D3", 0, "A2D3", 1));
        constraintList.add(new Constraint("A1D3", 0, "A3D3", 2));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA2D3() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A2D3", 2, "A2D2", 1));
        constraintList.add(new Constraint("A2D3", 2, "A2D1", 0));
        constraintList.add(new Constraint("A2D3", 1, "A1D3", 0));
        constraintList.add(new Constraint("A2D3", 1, "A3D3", 2));

        return constraintList;
    }

    public List<Constraint> getAllContstraintsForA3D3() {
        List<Constraint> constraintList = new ArrayList<>();

        constraintList.add(new Constraint("A3D3", 2, "A3D2", 1));
        constraintList.add(new Constraint("A3D3", 2, "A3D1", 0));
        constraintList.add(new Constraint("A3D3", 2, "A2D3", 1));
        constraintList.add(new Constraint("A3D3", 2, "A1D3", 0));

        return constraintList;
    }
}
