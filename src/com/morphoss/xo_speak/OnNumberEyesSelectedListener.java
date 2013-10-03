package com.morphoss.xo_speak;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class OnNumberEyesSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {

		/*Toast.makeText(
				parent.getContext(),
				"Number of eyes selected : "
						+ parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();*/
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}

}
