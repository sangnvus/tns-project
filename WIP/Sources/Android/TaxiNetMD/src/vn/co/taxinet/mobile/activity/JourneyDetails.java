package vn.co.taxinet.mobile.activity;

import vn.co.taxinet.mobile.model.Journey;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import vn.co.taxinet.mobile.R;

public class JourneyDetails extends Activity {

	private EditText starting_point, stopping_point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_journey_details);
		initialize();

		Bundle bd = getIntent().getExtras();
		if (bd.getSerializable("journey item details") != null) {
			Journey journey = (Journey) bd
					.getSerializable("journey item details");
			starting_point.setText(journey.getStarting_point());
			stopping_point.setText(journey.getStopping_point());
		}
	}

	private void initialize() {
		starting_point = (EditText) findViewById(R.id.starting_point);
		stopping_point = (EditText) findViewById(R.id.stopping_point);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.edit_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_edit:
			starting_point.setEnabled(true);
			stopping_point.setEnabled(true);
			Toast.makeText(getApplicationContext(), "Đã có thể chỉnh sửa",
					Toast.LENGTH_LONG).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
