package me.henrytao.mytwist;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;


public class MainActivity extends Activity {

  public final static String EXTRA_MESSAGE = "me.henrytao.mytwist.MESSAGE";

  private DrawerLayout mDrawerLayout;

  private ListView mDrawerList;

  private String[] mDrawerString;

  private ActionBarDrawerToggle mDrawerListener;

  private CharSequence mDrawerTitle;

  private CharSequence mTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction()
          .add(R.id.container, new PlaceholderFragment())
          .commit();
    }

    mDrawerTitle = mTitle = getTitle();

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerList = (ListView) findViewById(R.id.drawer_list);
    mDrawerString = getResources().getStringArray(R.array.drawer_list);

    mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDrawerString));
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    mDrawerListener = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
      }

      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
      }
    };
    mDrawerLayout.setDrawerListener(mDrawerListener);

    getActionBar().setHomeButtonEnabled(true);
    getActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    if(mDrawerListener.onOptionsItemSelected(item)){
      return true;
    }
    switch (item.getItemId()) {
      case R.id.action_search:
        return true;
      case R.id.action_settings:
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerListener.onConfigurationChanged(newConfig);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mDrawerListener.syncState();
  }

  /**
   * activity method
   */

  public void sendMessage(View view) {
    Intent intent = new Intent(this, DisplayMessageActivity.class);
    EditText editText = (EditText) findViewById(R.id.edit_message);
    String message = editText.getText().toString();
    intent.putExtra(EXTRA_MESSAGE, message);
    startActivity(intent);
  }

  /**
   * DrawerItemClick listener
   */
  private class DrawerItemClickListener implements ListView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      onDrawerItemClick(position);
    }
  }

  private void onDrawerItemClick(int position) {
    /*Toast.makeText(this, mDrawerString[position] + " was selected", Toast.LENGTH_SHORT).show();*/
    mDrawerList.setItemChecked(position, true);
    setTitle(mDrawerString[position]);
  }

  @Override
  public void setTitle(CharSequence title) {
    super.setTitle(title);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }


}
