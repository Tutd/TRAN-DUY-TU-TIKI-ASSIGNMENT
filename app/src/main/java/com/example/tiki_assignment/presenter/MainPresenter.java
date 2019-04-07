package com.example.tiki_assignment.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.tiki_assignment.model.FetchingKeyWordData;
import com.example.tiki_assignment.model.LoadKeyWordListener;
import com.example.tiki_assignment.view.MainView;

import java.util.ArrayList;

public class MainPresenter implements LoadKeyWordListener {

    private FetchingKeyWordData mFetchingData;
    private MainView mMainView;

    public MainPresenter(MainView mainView, Context context) {
        this.mMainView = mainView;
        mFetchingData = new FetchingKeyWordData(this, context);
    }

    public void loadKeyWordData() {
        mFetchingData.fetchingKeyWordData();
    }

    @Override
    public void onLoadKeyWordSuccess(@NonNull ArrayList<String> arrayList) {
        new ProcessDataAsyncTask().execute(arrayList);
    }

    @Override
    public void onLoadKeyWordFailure() {
        //TODO: nothing do do
    }

    private ArrayList<String> processData(@NonNull ArrayList<String> arrStr) {
        for (int i = 0; i < arrStr.size(); i++) {
            arrStr.set(i, calculateKeyWord(arrStr.get(i)));
        }
        return arrStr;
    }

    /**
     *  If the keyword is more than one word, then display in two lines.
     *  These two lines should have minimum difference in length
     * @param keyWord
     * @return
     */
    private String calculateKeyWord(String keyWord) {
        String text = keyWord.trim();
        text = text.replaceAll("\n" , " ");
        StringBuilder strBuilder = new StringBuilder(text);

        // collect space index
        ArrayList<Integer> arrSpaceIndex = new ArrayList<>();
        int spaceIndex = 0;
        while ((spaceIndex = text.indexOf(' ', spaceIndex + 1)) > 0) {
            arrSpaceIndex.add(spaceIndex);
        }

        if (arrSpaceIndex.size() > 0) {
            String strSub1;
            String strSub2;
            int minimumDiff = Integer.MAX_VALUE;
            int diff;
            int indexNewLine = -1;
            for (int i = 0; i < arrSpaceIndex.size(); i++) {
                strSub1 = text.substring(0, arrSpaceIndex.get(i));
                strSub2 = text.substring(strSub1.length() + 1);

                diff = Math.abs(strSub1.length() - strSub2.length());
                if (diff <= minimumDiff) {
                    minimumDiff = diff;
                    indexNewLine = arrSpaceIndex.get(i);
                } else {
                    break;
                }
            }

            if (indexNewLine > 0) {
                strBuilder.insert(indexNewLine, "\n");
            }
        }

        return strBuilder.toString();
    }

    private class ProcessDataAsyncTask extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {

        @Override
        protected final ArrayList<String> doInBackground(ArrayList<String>... arrayLists) {
            return processData(arrayLists[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<String> arrayList) {
            super.onPostExecute(arrayList);
            mMainView.displayKeywordList(arrayList);
        }
    }
}
