package algonquin.cst2335.brow1396.data;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> editString = new MutableLiveData<String>();
    public MutableLiveData<Boolean> isOn = new MutableLiveData<Boolean>();

}
