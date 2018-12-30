package blog.kiwinam.android.actionbarblog

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 툴바 사용법을 연습하는 액티비티
 * Created by kiwinam - 18.12.26
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var isNavigationOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolbar()

        navigationView.setNavigationItemSelectedListener(this)
    }

    // 툴바 사용 설정
    private fun setToolbar(){
        setSupportActionBar(toolbar)

        // 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)  // 왼쪽 버튼 이미지 설정
        supportActionBar!!.setDisplayShowTitleEnabled(false)    // 타이틀 안보이게 하기
    }

    // 툴바 메뉴 버튼을 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }

    // 툴바 메뉴 버튼이 클릭 됐을 때 콜백
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정한다.
        when(item!!.itemId){
            android.R.id.home->{ // 메뉴 버튼
                drawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
            R.id.menu_search-> Snackbar.make(toolbar,"Search menu pressed",Snackbar.LENGTH_SHORT).show()
            R.id.menu_account-> Snackbar.make(toolbar,"Account menu pressed",Snackbar.LENGTH_SHORT).show()
            R.id.menu_logout-> Snackbar.make(toolbar,"Logout menu pressed",Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


    // 네비게이션 드로어 메뉴 클릭 리스너
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){  // 네비게이션 메뉴가 클릭되면 스낵바가 나타난다.
            R.id.account->Snackbar.make(toolbar,"Navigation Account pressed",Snackbar.LENGTH_SHORT).show()
            R.id.setting->Snackbar.make(toolbar,"Navigation Setting pressed",Snackbar.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        return false
    }

    /*
     * 뒤로가기 버튼으로 네비게이션 닫기
     *
     * 네비게이션 드로어가 열려 있을 때 뒤로가기 버튼을 누르면 네비게이션을 닫고,
     * 닫혀 있다면 기존 뒤로가기 버튼으로 작동한다.
     */
    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers()
        }else{
            super.onBackPressed()
        }
    }

}
