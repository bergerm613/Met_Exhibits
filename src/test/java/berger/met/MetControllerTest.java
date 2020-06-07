package berger.met;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MetControllerTest
{

    @Test
    public void getDepartments()
    {
        //given
        MetService service = mock(MetService.class);
        Call<DepartmentsFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        JComboBox<DepartmentsFeed.Department> combo = mock(JComboBox.class);

        doReturn(call).when(service).getDepartments();
        MetController controller = new MetController(service, label, label, label, label, label, label);

        //when
        controller.getDepartments(combo);

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void populateDepartments()
    {
        //given
        MetService service = mock(MetService.class);
        Response<DepartmentsFeed> response = mock(Response.class);
        JLabel label = mock(JLabel.class);
        MetController controller = new MetController(service, label, label, label, label, label, label);

        List<DepartmentsFeed.Department> list = new ArrayList<>();
        DepartmentsFeed.Department dep = new DepartmentsFeed.Department();
        list.add(dep);
        JComboBox<DepartmentsFeed.Department> combo = mock(JComboBox.class);

        //when
        controller.populateDepartments(list, combo);

        //then
        verify(combo).addItem(dep);
    }

    @Test
    public void getDepartmentObjects()
    {
        //given
        MetService service = mock(MetService.class);
        Call<DepartmentsFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        int id = 4;

        doReturn(call).when(service).getDepartmentObjects(id);
        MetController controller = new MetController(service, label, label, label, label, label, label);

        //when
        controller.getDepartmentObjects(id);

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void getObject()
    {
        //given
        MetService service = mock(MetService.class);
        Call<DepartmentsFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        int id = 4;

        doReturn(call).when(service).getObject(id);
        MetController controller = new MetController(service, label, label, label, label, label, label);

        //when
        controller.getObject(id);

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void populateObject()
    {
        //given
        MetService service = mock(MetService.class);
        Response<DepartmentsFeed> response = mock(Response.class);
        JLabel label = mock(JLabel.class);
        MetController controller = new MetController(service, label, label, label, label, label, label);
        ObjectFeed feed = new ObjectFeed();
        feed.title = "Test Title";
        feed.culture = "Test Culture";
        feed.medium = "Test Medium";
        feed.artistDisplayName = "Test Artist";
        feed.classification = "Test Classification";

        doReturn(feed).when(response).body();

        //when
        controller.populateObject(feed);

        //then
        verify(label).setText("Test Title");
        verify(label).setText("Test Culture");
        verify(label).setText("Test Medium");
        verify(label).setText("Test Artist");
        verify(label).setText("Test Classification");
    }
}