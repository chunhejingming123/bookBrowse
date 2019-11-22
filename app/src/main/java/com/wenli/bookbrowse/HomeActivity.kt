package com.wenli.bookbrowse

import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.wenli.bookbrowse.fragment.HomeFragment
import com.wenli.bookbrowse.fragment.LoginFragment
import com.wenli.bookbrowse.fragment.ZxingFragment
import com.wenli.bookbrowse.fragment.UserFragment
import com.wenli.framework.base.BaseActivity
import com.wenli.framework.base.BaseApplication
import com.wenli.framework.bottomnavigation.BottomNavigationBar
import com.wenli.framework.bottomnavigation.BottomNavigationItem
import com.wenli.framework.util.ParmarsValue
import com.wenli.framework.util.PermissionsUtils
import com.wenli.framework.util.ScreenUtil
import com.wenli.framework.util.SharedPreferencesHelper

class HomeActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {
    var homeFragment: HomeFragment? = null
    var scanFragment: ZxingFragment? = null
    var userFragment: UserFragment? = null
    var loginFragment: LoginFragment? = null
    var bottomBar: BottomNavigationBar? = null
    internal var handler: Handler? = Handler()
    var selectIndex: Int = 0
    override fun onLayoutLoad(): Int {
        return R.layout.activity_main
    }

    override fun findView() {
        bottomBar = findViewById(R.id.bottomBar)
        rendView()
        //  checkVersion()

    }

    override fun rendView() {
        ScreenUtil.initScreen(this)
        bottomBar?.setMode(BottomNavigationBar.MODE_FIXED)
        bottomBar?.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        bottomBar?.addItem(BottomNavigationItem(R.mipmap.ic_find, getString(R.string.find)).setActiveColorResource(R.color.maincolor).setInActiveColorResource(R.color.colortabun))
                ?.addItem(BottomNavigationItem(R.drawable.ic_bookborse, getString(R.string.bookbrowse)).setActiveColorResource(R.color.maincolor).setInActiveColorResource(R.color.colortabun))
                ?.addItem(BottomNavigationItem(R.mipmap.ic_user, getString(R.string.mine)).setActiveColorResource(R.color.maincolor).setInActiveColorResource(R.color.colortabun))
                ?.setFirstSelectedPosition(selectIndex)
                ?.initialise()
        bottomBar?.setTabSelectedListener(this)
        initFragment(selectIndex)

    }

    private fun initFragment(currentIndex: Int) {
        var isLogin = SharedPreferencesHelper.getInstance(this).getSharedPreference(ParmarsValue.KEY_lOGIN, false) as Boolean
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        hidtFragment(fragmentTransaction)
        selectIndex = currentIndex
        when (currentIndex) {
            0 -> if (null == homeFragment) {
                homeFragment = HomeFragment()
                fragmentTransaction.add(R.id.container, homeFragment!!)
            } else {
                fragmentTransaction.show(homeFragment!!)
            }
            1 -> {
                if (ContextCompat.checkSelfPermission(BaseApplication.application, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this!!, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), PermissionsUtils.REQUEST_PHOTE_PERSION)
                    return
                }
                if (null == scanFragment) {
                    scanFragment = ZxingFragment()
                    fragmentTransaction.add(R.id.container, scanFragment!!)
                } else {
                    fragmentTransaction.show(scanFragment!!)
                }

            }
            2 -> {
                if (null == userFragment) {
                    userFragment = UserFragment()
                    fragmentTransaction.add(R.id.container, userFragment!!)
                } else {
                    fragmentTransaction.show(userFragment!!)
                }

//                if (isLogin) {
//                    if (null == userFragment) {
//                        userFragment = UserFragment()
//                        fragmentTransaction.add(R.id.container, userFragment!!)
//                    } else {
//                        fragmentTransaction.show(userFragment!!)
//                    }
//                } else {
//                    if (null == loginFragment) {
//                        loginFragment = LoginFragment()
//                        fragmentTransaction.add(R.id.container, loginFragment!!)
//                    } else {
//                        fragmentTransaction.show(loginFragment!!)
//                    }
//                }

            }


        }
        fragmentTransaction.commitAllowingStateLoss()

    }


    fun hidtFragment(fragmentTransaction: FragmentTransaction) {
        if (null != homeFragment) {
            fragmentTransaction.hide(homeFragment!!)
        }
        if (null != scanFragment) {
            fragmentTransaction.hide(scanFragment!!)
        }
        if (null != userFragment) {
            fragmentTransaction.hide(userFragment!!)
        }
        if (null != loginFragment) {
            fragmentTransaction.hide(loginFragment!!)
        }

    }

    override fun onTabSelected(position: Int) {
        initFragment(position)
    }

    override fun onTabUnselected(position: Int) {
    }

    override fun onTabReselected(position: Int) {
    }
}
