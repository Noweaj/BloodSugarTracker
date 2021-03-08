package com.noweaj.android.bloodsugartracker.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.noweaj.android.bloodsugartracker.callback.SwipeCallback

enum class ButtonState{
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE
}

class SwipeController(
    private val callback: SwipeCallback
): ItemTouchHelper.Callback() {
    
    private val TAG = SwipeController::class.java.simpleName
    
    private var swipeBack = false
    private var buttonShowedState = ButtonState.GONE
    private val buttonWidth: Float = 300F
    
    private var buttonInstance: RectF? = null
    private var currentItemViewHolder: RecyclerView.ViewHolder? = null
    
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, LEFT or RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if(swipeBack){
            swipeBack = buttonShowedState != ButtonState.GONE
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ACTION_STATE_SWIPE){
            if(buttonShowedState != ButtonState.GONE){
                var newDX = 0F
                if(buttonShowedState == ButtonState.LEFT_VISIBLE) newDX = Math.max(dX, buttonWidth)
                if(buttonShowedState == ButtonState.RIGHT_VISIBLE) newDX = Math.min(dX, -buttonWidth)
                super.onChildDraw(c, recyclerView, viewHolder, newDX, dY, actionState, isCurrentlyActive)
            } else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        if(buttonShowedState == ButtonState.GONE){
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
        
        drawButtons(c, viewHolder)
        currentItemViewHolder = viewHolder
    }
    
    private fun setTouchListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ){
        recyclerView.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                swipeBack = event!!.getAction() == MotionEvent.ACTION_CANCEL
                        || event!!.getAction() == MotionEvent.ACTION_UP
                
                if(swipeBack){
                    if(dX < -buttonWidth)
                        buttonShowedState = ButtonState.RIGHT_VISIBLE
                    else if(dX > buttonWidth)
                        buttonShowedState = ButtonState.LEFT_VISIBLE
                    
                    if(buttonShowedState != ButtonState.GONE){
                        setTouchDownListener(
                            c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive
                        )
                        setItemsClickable(recyclerView, false)
                    }
                }
                
                return false
            }
        })
    }
    
    private fun setTouchDownListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ){
        recyclerView.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event!!.action == MotionEvent.ACTION_DOWN){
                    setTouchUpListener(
                        c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive
                    )
                }
                return false
            }
        })
    }

    private fun setTouchUpListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ){
        recyclerView.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event!!.action == MotionEvent.ACTION_UP){
                    super@SwipeController.onChildDraw(
                        c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive
                    )
                    recyclerView.setOnTouchListener(object: View.OnTouchListener{
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            return false
                        }
                    })
                    setItemsClickable(recyclerView, true)
                    swipeBack = false
                    
                    buttonInstance?.let{
                        if(it.contains(event.getX(), event.getY())){
                            if(buttonShowedState == ButtonState.LEFT_VISIBLE){
                                // callback on edit
                                callback.onEditClicked(viewHolder.adapterPosition)
                            } else if(buttonShowedState == ButtonState.RIGHT_VISIBLE){
                                // callback on delete
                                callback.onDeleteClicked(viewHolder.adapterPosition)
                            }
                        }
                    }
                    
                    buttonShowedState = ButtonState.GONE
                    currentItemViewHolder = null
                }
                return false
            }
        })
    }
    
    private fun setItemsClickable(
        recyclerView: RecyclerView,
        isClickable: Boolean
    ){
        for(i in 0 until recyclerView.childCount){
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }
    
    private fun drawButtons(
        c: Canvas,
        viewHolder: RecyclerView.ViewHolder
    ){
        val buttonWidthWithoutPadding = buttonWidth - 20
        val corners = 16F
        val itemView = viewHolder.itemView
        val p = Paint()
        
        val editButton = RectF(
            itemView.left.toFloat(),
            itemView.top.toFloat(),
            itemView.left + buttonWidthWithoutPadding,
            itemView.bottom.toFloat()
        )
        p.color = Color.BLUE
        c.drawRoundRect(editButton, corners, corners, p)
        drawText("EDIT", c, editButton, p)
        
        val deleteButton = RectF(
            itemView.right.toFloat() - buttonWidthWithoutPadding,
            itemView.top.toFloat(),
            itemView.right.toFloat(),
            itemView.bottom.toFloat()
        )
        p.color = Color.RED
        c.drawRoundRect(deleteButton, corners, corners, p)
        drawText("DELETE", c, deleteButton, p)
        
        buttonInstance = null
        if(buttonShowedState == ButtonState.LEFT_VISIBLE){
            Log.d(TAG, "left visible")
            buttonInstance = editButton
        } else if(buttonShowedState == ButtonState.RIGHT_VISIBLE) {
            Log.d(TAG, "right visible")
            buttonInstance = deleteButton
        }
    }
    
    private fun drawText(
        text: String,
        c: Canvas,
        button: RectF,
        p: Paint
    ){
        val textSize = 40F
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize
        
        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX()-(textWidth/2), button.centerY()+(textSize/2), p)
    }
    
    fun onDraw(
        c: Canvas
    ){
        currentItemViewHolder?.let { drawButtons(c, it) }
    }
}