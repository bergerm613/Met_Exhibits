package berger.met;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class MetController
{
    private MetService service;
    private JLabel title;
    private JLabel culture;
    private JLabel medium;
    private JLabel artist;
    private JLabel classification;
    private JLabel picture;

    private int currIndex;
    private List<Integer> objectIds;

    public MetController(MetService service,
                         JLabel title,
                         JLabel culture,
                         JLabel medium,
                         JLabel artist,
                         JLabel classification,
                         JLabel picture)
    {
        this.service = service;
        this.title = title;
        this.culture = culture;
        this.medium = medium;
        this.artist = artist;
        this.classification = classification;
        this.picture = picture;
    }

    public void getDepartments(JComboBox<DepartmentsFeed.Department> depts)
    {
        service.getDepartments().enqueue(new Callback<DepartmentsFeed>()
        {
            @Override
            public void onResponse(Call<DepartmentsFeed> call, Response<DepartmentsFeed> response)
            {
                List<DepartmentsFeed.Department> departments = response.body().departments;

                populateDepartments(departments, depts);
            }

            @Override
            public void onFailure(Call<DepartmentsFeed> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

    public void populateDepartments(List<DepartmentsFeed.Department> departments, JComboBox<DepartmentsFeed.Department> depts)
    {
        for (DepartmentsFeed.Department dep : departments)
        {
            depts.addItem(dep);
        }
    }

    public void getDepartmentObjects(int departmentId)
    {
        service.getDepartmentObjects(departmentId).enqueue(new Callback<DepartmentObjectsFeed>()
        {
            @Override
            public void onResponse(Call<DepartmentObjectsFeed> call, Response<DepartmentObjectsFeed> response)
            {
                objectIds = response.body().objectIDs;
                currIndex = 0;
                getObject(objectIds.get(currIndex));
            }

            @Override
            public void onFailure(Call<DepartmentObjectsFeed> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

    public void getObject(int objectId)
    {
        service.getObject(objectId).enqueue(new Callback<ObjectFeed>()
        {
            @Override
            public void onResponse(Call<ObjectFeed> call, Response<ObjectFeed> response)
            {
                ObjectFeed feed = response.body();

                populateObject(feed);
            }

            @Override
            public void onFailure(Call<ObjectFeed> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

    public void populateObject(ObjectFeed feed)
    {
        title.setText(feed.title);
        culture.setText(feed.culture);
        medium.setText(feed.medium);
        artist.setText(feed.artistDisplayName);
        classification.setText(feed.classification);

        try
        {
            URL url = new URL(feed.primaryImage);
            Image image = ImageIO.read(url);
            image = image.getScaledInstance(250,250,Image.SCALE_SMOOTH);
            picture.setIcon(new ImageIcon(image));
        } catch (Exception e)
        {
            picture.setIcon(new ImageIcon());
        }
    }

    public void getNextObject()
    {
        currIndex = (currIndex == objectIds.size()-1) ?
                    0 :
                    ++currIndex;
        getObject(objectIds.get(currIndex));
    }

    public void getLastObject()
    {
        currIndex = (currIndex == 0) ?
                    objectIds.size()-1 :
                    --currIndex;
        getObject(objectIds.get(currIndex));
    }
}
