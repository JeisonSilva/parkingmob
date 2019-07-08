package com.jsonapp.parkingmob.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.jsonapp.parkingmob.R;

public class ExportDataDialogImpl extends DialogFragment{
    private static final String DIALOG_TAG = "EXPORT_DATA";
    public static final int EXPORT_DATA_AND_KEEP = 0;
    public static final int EXPORT_DATA_WITHOUT_KEEPING_THEM = 1;
    private ExportDataDialog exportListener;
    private RadioButton rb_export_data_and_keep_data;
    private RadioButton rb_export_data_without_keeping_them;

    public static ExportDataDialogImpl newDialog(){
        return new ExportDataDialogImpl();
    }

    public interface ExportDataDialog{
        void exportData(int opcao);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof ExportDataDialog){
            this.exportListener = (ExportDataDialog)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.export_data_dialog, container, false);
        this.rb_export_data_and_keep_data = view.findViewById(R.id.rb_export_and_keep_data);
        this.rb_export_data_without_keeping_them = view.findViewById(R.id.rb_export_data_without_keeping_them);

        this.rb_export_data_and_keep_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportListener.exportData(EXPORT_DATA_AND_KEEP);
                dismiss();
            }
        });

        this.rb_export_data_without_keeping_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportListener.exportData(EXPORT_DATA_WITHOUT_KEEPING_THEM);
                dismiss();
            }
        });

        return view;
    }

    public void openDialog(FragmentManager fm){
        if(fm.findFragmentByTag(DIALOG_TAG) == null){
            show(fm, DIALOG_TAG);
        }
    }
}
