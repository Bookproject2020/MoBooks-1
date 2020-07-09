package com.example.mobooks;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;

public class PdfViewerActivity extends AppCompatActivity {

    private PDFView mPDFView;
    //ArrayList<DataManager> getItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPDFView = findViewById(R.id.pdfViewer);
        String getItem = getIntent().getStringExtra("pdfFileName");
        //String getItem = getIntent().getExtras().getString("pdfFileName");


        mPDFView.fromAsset("Bob_Marley_A_Biography.pdf")
                .defaultPage(0)
                    .enableAnnotationRendering(true)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .spacing(0)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableAntialiasing(true)
                    .onPageError(new OnPageErrorListener() {
                        @Override
                        public void onPageError(int page, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Page error!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .onError(new OnErrorListener() {
                        @Override
                        public void onError(Throwable t) {
                            Toast.makeText(PdfViewerActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                            mPDFView.fitToWidth();
                        }
                    })
                    .load();










//        if (getItem.equals("Bob Marley Biography")){
//            mPDFView.fromAsset("Bob_Marley_A_Biography.pdf")
//                    .defaultPage(0)
//                    .enableAnnotationRendering(true)
//                    .scrollHandle(new DefaultScrollHandle(this))
//                    .spacing(0)
//                    .enableSwipe(true)
//                    .swipeHorizontal(false)
//                    .enableAntialiasing(true)
//                    .onPageError(new OnPageErrorListener() {
//                        @Override
//                        public void onPageError(int page, Throwable t) {
//                            Toast.makeText(getApplicationContext(), "Page error!", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .onError(new OnErrorListener() {
//                        @Override
//                        public void onError(Throwable t) {
//                            Toast.makeText(PdfViewerActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .onRender(new OnRenderListener() {
//                        @Override
//                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
//                            mPDFView.fitToWidth();
//                        }
//                    })
//                    .load();
//        }
    }

    //The back button has to be clicked twice before it exits
    private long onBackPressTime;
    private Toast backToast;

    @Override
    public void onBackPressed() {
        if (onBackPressTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            backToast.cancel();
            return;
        } else {
            backToast = Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        onBackPressTime = System.currentTimeMillis();
    }

}