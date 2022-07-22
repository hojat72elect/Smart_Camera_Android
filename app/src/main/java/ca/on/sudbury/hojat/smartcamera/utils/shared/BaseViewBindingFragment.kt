package ca.on.sudbury.hojat.smartcamera.utils.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


abstract class BaseViewBindingFragment<T : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
) : BaseFragment() {

    private var safeBinding: T? = null

    /**
     * Access type safe views from [layout]
     *
     * This should only be accessed between [onViewCreated] and [onDestroyView] lifecycles
     *
     * @throws KotlinNullPointerException if accessed outside of correct lifecycle as mentioned above
     */
    val binding: T get() = safeBinding!!

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(inflate(inflater, container, false)) {
            safeBinding = this
            return root
        }
    }

    override fun onDestroyView() {
        safeBinding = null
        super.onDestroyView()
    }
}
