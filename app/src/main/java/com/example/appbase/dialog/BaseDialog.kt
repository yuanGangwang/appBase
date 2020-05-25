package com.example.appbase.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDialog


class BaseDialog(context: Context, themeResId: Int) :
    AppCompatDialog(context, themeResId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }


    open class Builder(
        context: Context,
        themeResId: Int
    ) {
        var mTheme: Int = 0
        var cancelable: Boolean = false

        var controller: BaseDialogController

        init {
            mTheme = themeResId
            controller = BaseDialogController(context, BaseDialogController.DialogType.Common)
        }

        private var mContext: Context = context

        fun setTitle(@StringRes titleId: Int): Builder {
            controller.setTitle(mContext.getText(titleId))
            return this
        }

        fun setTitle(title: CharSequence?): Builder {
            controller.setTitle(title)
            return this
        }

        fun setMessage(@StringRes messageId: Int): Builder {
            controller.setMessage(mContext.getText(messageId))
            return this
        }

        fun setMessage(message: CharSequence?): Builder {
            controller.setMessage(message)
            return this
        }

        fun setIcon(@DrawableRes iconId: Int): Builder {
            controller.setTitleIcon(iconId)
            return this
        }

        fun setCustomerView(
            view: View
        ): Builder {
            controller.setCustomContent(view)
            return this
        }

        fun setCanCancel(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun setLeftButton(
            @StringRes textId: Int,
            listener: View.OnClickListener
        ): Builder {
            this.setLeftButton(16f, 0, mContext.getString(textId), listener)
            return this
        }

        private fun setLeftButton(
            textSize: Float,
            textColor: Int,
            text: CharSequence,
            listener: View.OnClickListener
        ): Builder {
            controller.setButton(
                BaseDialogController.BaseDialogInterFace.BUTTON_LEFT,
                textSize,
                textColor,
                text,
                listener
            )
            return this
        }

        fun setLeftButton(
            text: CharSequence, listener: View.OnClickListener
        ): Builder {
            this.setLeftButton(16f, 0, text, listener)
            return this
        }


        fun setRightButton(
            @StringRes textId: Int,
            listener: View.OnClickListener
        ): Builder {
            this.setRightButton(16f, 0, mContext.getString(textId), listener)
            return this
        }


        fun setRightButton(
            text: CharSequence, listener: View.OnClickListener
        ): Builder {
            this.setRightButton(16f, 0, text, listener)
            return this
        }

        fun setRightButton(
            textSize: Float,
            textColor: Int,
            text: CharSequence,
            listener: View.OnClickListener
        ): Builder {
            controller.setButton(
                BaseDialogController.BaseDialogInterFace.BUTTON_RIGHT,
                textSize,
                textColor,
                text,
                listener
            )
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

         fun create(): BaseDialog {

            val dialog = BaseDialog(mContext, mTheme)
            dialog.setContentView(controller.setupView())
            dialog.setCancelable(cancelable)
            if (cancelable) {
                dialog.setCanceledOnTouchOutside(true)
            }
            return dialog
        }


        fun show(): BaseDialog {
            val dialog = create()
            dialog.show()
            return dialog
        }


    }


    class ImageBuilder(context: Context, themeResId: Int) : Builder(context, themeResId) {

        init {
            mTheme = themeResId
            controller = BaseDialogController(context, BaseDialogController.DialogType.Img)
        }
        fun setDealImg(dealDialogImg: BaseDialogController.DealDialogImg): ImageBuilder {
            controller.setDealImg(dealDialogImg)
            return this
        }

    }
}

