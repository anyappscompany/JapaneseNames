package ua.com.anyapps.japanesenames;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<String> femaleNames;
    private List<String> femaleOriginalNames;
    private List<String> femaleNamesMeaning;

    private List<String> maleNames;
    private List<String> maleOriginalNames;
    private List<String> maleNamesMeaning;

    private List<String> surnames;
    private List<String> originalSurnames;
    private List<String> surnamesMeaning;

    private int totalFemaleNames = 0;
    private int totalMaleNames = 0;
    private int totalSurnames = 0;
    private final String TAG = "debapp";

    private CheckBox cbName;
    private CheckBox cbSurname;
    private Spinner spGender;
    private TextView tvResultName;
    private TextView tvResultSurname;
    private TextView tvOriginalName;
    private TextView tvOriginalSurname;
    private TextView tvMeaningName;
    private TextView tvMeaningSurname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbName = findViewById(R.id.cbName);
        cbSurname = findViewById(R.id.cbSurname);
        spGender = findViewById(R.id.spGender);
        tvResultName = findViewById(R.id.tvResultName);
        tvResultSurname = findViewById(R.id.tvResultSurname);
        tvOriginalName = findViewById(R.id.tvOriginalName);
        tvOriginalSurname = findViewById(R.id.tvOriginalSurname);
        tvMeaningName = findViewById(R.id.tvMeaningName);
        tvMeaningSurname = findViewById(R.id.tvMeaningSurname);

        femaleNames = Arrays.asList(getResources().getStringArray(R.array.female_names));
        femaleOriginalNames = Arrays.asList(getResources().getStringArray(R.array.female_original_name));
        femaleNamesMeaning = Arrays.asList(getResources().getStringArray(R.array.female_names_meaning));

        maleNames = Arrays.asList(getResources().getStringArray(R.array.male_names));
        maleOriginalNames = Arrays.asList(getResources().getStringArray(R.array.male_original_name));
        maleNamesMeaning = Arrays.asList(getResources().getStringArray(R.array.male_names_meaning));

        surnames = Arrays.asList(getResources().getStringArray(R.array.surnames));
        originalSurnames = Arrays.asList(getResources().getStringArray(R.array.original_surnames));
        surnamesMeaning = Arrays.asList(getResources().getStringArray(R.array.surnames_meaning));

        totalFemaleNames = femaleNames.size();
        totalMaleNames = maleNames.size();
        totalSurnames = surnames.size();
    }

    public void startBtn_Click(View v){
        String resultName = "";
        String resultSurname = "";
        String originalName = "";
        String originalSurname = "";
        String meaningName = "";
        String meaningSurname = "";

        if(cbName.isChecked() || cbSurname.isChecked()){
            Random r = new Random();
            //Log.d(TAG, r.nextInt(totalFemaleNames) + "");
            switch(spGender.getSelectedItemPosition()){
                // любой пол
                case 0:
                    if(cbName.isChecked()) {
                        if (r.nextInt(2) == 0) {
                            // мужские имена
                            int indexM = r.nextInt(totalMaleNames);
                            resultName += maleNames.get(indexM);
                            originalName += maleOriginalNames.get(indexM);
                            meaningName += maleNamesMeaning.get(indexM);
                        } else {
                            // женские имена
                            int indexF = r.nextInt(totalFemaleNames);
                            resultName += femaleNames.get(indexF);
                            originalName += femaleOriginalNames.get(indexF);
                            meaningName += femaleNamesMeaning.get(indexF);
                        }
                    }
                    break;
                    // мужские
                case 1:
                    int indexM = r.nextInt(totalMaleNames);
                    resultName += maleNames.get(indexM);
                    originalName += maleOriginalNames.get(indexM);
                    meaningName += maleNamesMeaning.get(indexM);
                    break;
                    // женские
                case 2:
                    int indexF = r.nextInt(totalFemaleNames);
                    resultName += femaleNames.get(indexF);
                    originalName += femaleOriginalNames.get(indexF);
                    meaningName += femaleNamesMeaning.get(indexF);
                    break;
            }

            if(cbSurname.isChecked()){
                int indexS = r.nextInt(totalSurnames);
                resultSurname += surnames.get(indexS);
                originalSurname += originalSurnames.get(indexS);
                meaningSurname += surnamesMeaning.get(indexS);
            }
        }
        tvResultName.setText(resultName);
        tvResultSurname.setText(resultSurname);

        tvOriginalName.setText(originalName);
        tvOriginalSurname.setText(originalSurname);

        tvMeaningName.setText(meaningName);
        tvMeaningSurname.setText(meaningSurname);
    }

    public void copyBtn_Click(View v){
        String result = "";
        result = tvResultName.getText().toString() + tvResultSurname.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", result);
        clipboard.setPrimaryClip(clip);

        Toast toast = Toast.makeText(getApplicationContext(),
                result, Toast.LENGTH_SHORT);
        toast.show();
    }
}
