package berger.met;

import java.util.List;

public class DepartmentsFeed
{
    List<Department> departments;

    static class Department {
        int departmentId;
        String displayName;

        @Override
        public String toString()
        {
            return displayName;
        }
    }
}
