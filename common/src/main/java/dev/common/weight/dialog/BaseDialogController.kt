package dev.common.weight.dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import dev.common.R

open class BaseDialogController(private var mContext: Context, private var dialogType: DialogType) {

    enum class DialogType {
        Common, Img
    }

    enum class BaseDialogInterFace {
        BUTTON_LEFT,
        BUTTON_RIGHT
    }

    private val dialogLayout: View = LayoutInflater.from(mContext)
        .inflate(R.layout.dialog_base_layout, null)
    private var title: TextView? = null
    private var titleIcon: ImageView? = null
    private var contentView: FrameLayout? = null
    private var dlgBottomLayout: FrameLayout? = null
    private var rightBtn: TextView? = null
    private var leftBtn: TextView? = null
    private var dlg_desc: TextView? = null

    private var leftTextStyle: TextStyle? = null
    private var rightTextStyle: TextStyle? = null


    fun setButton(
        whichButton: BaseDialogInterFace,
        textSize: Float,
        textColor: Int,
        text: CharSequence,
        listener: View.OnClickListener
    ) {
        when (whichButton) {
            BaseDialogInterFace.BUTTON_LEFT -> {
                leftTextStyle = TextStyle(textSize, textColor, text, listener)
            }
            BaseDialogInterFace.BUTTON_RIGHT -> {
                rightTextStyle = TextStyle(textSize, textColor, text, listener)
            }
            else -> throw IllegalArgumentException("Button does not exist")
        }

    }

    fun setupView(): View {

        var commonLayout: View = dialogLayout.findViewById(R.id.dlgCommonLayout)
        var imgLayout: View = dialogLayout.findViewById(R.id.dlgImgLayout)

        Log.i("dialog",dialogType.name)
        if (dialogType == DialogType.Common) {
            imgLayout.visibility = View.GONE
            commonLayout.visibility = View.VISIBLE
            applyCommonDialog()
        }


        if (dialogType == DialogType.Img) {
            imgLayout.visibility = View.VISIBLE
            commonLayout.visibility = View.GONE
            applyPicDialog()
        }

        return dialogLayout
    }

    private fun applyCommonDialog() {
        title = dialogLayout.findViewById(R.id.dlg_title_txt)
        titleIcon = dialogLayout.findViewById(R.id.dlg_title_icon)
        contentView = dialogLayout.findViewById(R.id.contentView)
        dlg_desc = dialogLayout.findViewById(R.id.dlg_desc)
        dlgBottomLayout = dialogLayout.findViewById(R.id.dlg_bottom)
        leftBtn = dialogLayout.findViewById(R.id.dlg_btn_left)
        rightBtn = dialogLayout.findViewById(R.id.dlg_btn_right)

        if (mTitle.isNullOrEmpty()) {
            title?.visibility = View.GONE
        } else {
            title?.text = mTitle
        }

        leftTextStyle?.apply(leftBtn!!)
        rightTextStyle?.apply(rightBtn!!)


        if (!mMessage.isNullOrEmpty()) {
            dlg_desc?.text = mMessage
        } else {
            dlg_desc?.visibility = View.GONE
        }

        if (customContentView != null) {
            contentView?.removeView(dlg_desc)
            contentView?.addView(customContentView)
        }

        if (titleResId > 0) {
            title?.visibility = View.GONE
            titleIcon?.setImageResource(titleResId)
        }
    }


    private fun applyPicDialog() {
        dealDialogImg?.onImgDeal(getImageContent())
    }

    private var mTitle: CharSequence? = null
    private var mMessage: CharSequence? = null
    fun setTitle(title: CharSequence?) {
        mTitle = title
    }

    var customContentView: View? = null

    fun setCustomContent(customContentView: View) {
        this.customContentView = customContentView
    }

    fun setMessage(message: CharSequence?) {
        mMessage = message
    }

    var titleResId = -1
    fun setTitleIcon(resId: Int) {
        this.titleResId = resId
    }

    fun getImageContent(): ImageView? {
        if (dialogType == DialogType.Img) {
            return dialogLayout.findViewById(R.id.tipImg)
        }

        return null;
    }

    interface DealDialogImg {
        fun onImgDeal(img: ImageView?);
    }

    var dealDialogImg: DealDialogImg? = null

    fun setDealImg(dealDialogImg: DealDialogImg) {
        this.dealDialogImg = dealDialogImg
    }

    class TextStyle {
        var textSize: Float = 16F
        var textColor: Int = -1
        var text: CharSequence = ""
        var onClickListener: View.OnClickListener? = null

        constructor(
            textSize: Float,
            textColor: Int,
            text: CharSequence,
            onClickListener: View.OnClickListener
        ) {
            this.textSize = textSize
            this.textColor = textColor
            this.text = text
            this.onClickListener = onClickListener
        }

        fun apply(textView: TextView) {
            textView.text = text
            textView.textSize = textSize
            if (textColor > 0)
                textView.setTextColor(textColor)
            if (onClickListener != null)
                textView.setOnClickListener(onClickListener)
        }
    }
}