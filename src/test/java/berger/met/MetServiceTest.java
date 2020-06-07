package berger.met;

import berger.met.*;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MetServiceTest
{
    @Test
    public void getDepartments() throws IOException
    {
        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        DepartmentsFeed feed = service.getDepartments().execute().body();

        //then
        assertNotNull(feed);
        List<DepartmentsFeed.Department> departments = feed.departments;
        assertFalse(departments.isEmpty());

        for (DepartmentsFeed.Department dep : departments)
        {
            System.out.println(dep.displayName);
        }
    };

    @Test
    public void getDepartmentObjects() throws IOException
    {
        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        DepartmentObjectsFeed feed = service.getDepartmentObjects(12).execute().body();

        //then
        assertNotNull(feed);
        List<Integer> objectIds = feed.objectIDs;
        assertFalse(objectIds.isEmpty());

        System.out.println(objectIds);
    }

    @Test
    public void getObject() throws IOException
    {
        //given
        MetService service = new MetServiceFactory().getInstance();

        //when
        ObjectFeed feed = service.getObject(501607).execute().body();

        //then
        assertNotNull(feed);

        System.out.println(feed.objectID);
        System.out.println(feed.title);
        System.out.println(feed.accessionYear);
        System.out.println(feed.artistDisplayName);
        System.out.println(feed.classification);
        System.out.println(feed.culture);
        System.out.println(feed.medium);
        System.out.println(feed.primaryImage);
    }
}
