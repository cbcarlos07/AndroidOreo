package br.com.brito.agendadecontatos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickIistener mListener;
    private GestureDetector mGestureDetector;

    public interface  OnItemClickIistener {
        void onItemClick(View view, int posicao );
    }
    public RecyclerItemClickListener( Context context, OnItemClickIistener listener ){
        mListener = listener;
        mGestureDetector = new GestureDetector( context, new GestureDetector.SimpleOnGestureListener(){
            public boolean onSigleTapUp( MotionEvent e ){
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View childView = recyclerView.findChildViewUnder( motionEvent.getX(), motionEvent.getY() );
        if( childView != null  && mListener != null && mGestureDetector.onTouchEvent(motionEvent) ){
            mListener.onItemClick( childView, recyclerView.getChildAdapterPosition( childView ) );
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
