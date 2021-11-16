package com.example.worldskillsshop.ViewPager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapte(fragment:FragmentActivity,var Image_1:String,var Image_2: String,var Image_3: String, var Image_4: String): FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int {
        return prover()
    }


     override fun createFragment(position: Int): Fragment{
        val fragment = ViewPager_image()
        fragment.arguments = Bundle().apply{


            when(prover()){
                1-> if (Image_1!="0"&&Image_2=="0"&&Image_3=="0"&&Image_4=="0"){
                    when(position){
                        0->putString(IMAGEVP_1, Image_1)
                    }
                }else if (Image_1=="0"&&Image_2!="0"&&Image_3=="0"&&Image_4=="0"){
                    when(position){
                        0->putString(IMAGEVP_2, Image_2)
                    }
                }else if (Image_1=="0"&&Image_2=="0"&&Image_3!="0"&&Image_4=="0"){
                    when(position){
                        0->putString(IMAGEVP_3, Image_3)
                    }
                }else if (Image_1=="0"&&Image_2=="0"&&Image_3=="0"&&Image_4!="0"){
                    when(position){
                        0->putString(IMAGEVP_4, Image_4)
                    }
                }
                2-> if (Image_1=="0"&&Image_2=="0"&&Image_3!="0"&&Image_4!="0"){
                    when(position){
                        0->putString(IMAGEVP_3, Image_3)
                        1->putString(IMAGEVP_4, Image_4)
                    }
                }else if (Image_1!="0"&&Image_2=="0"&&Image_3!="0"&&Image_4=="0"){
                    when(position){
                        0->putString(IMAGEVP_1, Image_1)
                        1->putString(IMAGEVP_3, Image_3)
                    }
                }else if (Image_1=="0"&&Image_2!="0"&&Image_3!="0"&&Image_4=="0"){
                    when(position){
                        0->putString(IMAGEVP_2, Image_2)
                        1->putString(IMAGEVP_3, Image_3)
                    }
                }else if (Image_1!="0"&&Image_2!="0"&&Image_3=="0"&&Image_4=="0"){
                    when(position){
                        0->putString(IMAGEVP_1, Image_1)
                        1->putString(IMAGEVP_2, Image_2)
                    }
                }else if(Image_1=="0"&&Image_2!="0"&&Image_3=="0"&&Image_4!="0"){
                    when(position){
                        0->putString(IMAGEVP_1, Image_1)
                        1->putString(IMAGEVP_4, Image_4)
                    }

                }else if (Image_1=="0"&&Image_2!="0"&&Image_3=="0"&&Image_4!="0"){
                    when(position){
                        0->putString(IMAGEVP_2, Image_2)
                        1->putString(IMAGEVP_4, Image_4)
                    }
                }
                3-> if (Image_1!="0"&&Image_2=="0"&&Image_3!="0"&&Image_4!="0"){
                    when(position){
                        0->putString(IMAGEVP_1, Image_1)
                        1->putString(IMAGEVP_3, Image_3)
                        2->putString(IMAGEVP_4, Image_4)
                    }
                }else if (Image_1=="0"&&Image_2!="0"&&Image_3!="0"&&Image_4!="0"){
                    when(position){
                        0->putString(IMAGEVP_2, Image_2)
                        1->putString(IMAGEVP_3, Image_3)
                        2-> putString(IMAGEVP_4, Image_4)
                    }
                }else if (Image_1!="0"&&Image_2!="0"&&Image_3!="0"&&Image_4=="0"){
                    when(position){
                        0->putString(IMAGEVP_1, Image_1)
                        1->putString(IMAGEVP_3, Image_3)
                        2->putString(IMAGEVP_2, Image_2)
                    }
                }else if (Image_1!="0"&&Image_2!="0"&&Image_3=="0"&&Image_4!="0"){
                    when(position){
                        0-> putString(IMAGEVP_1, Image_1)
                        1->putString(IMAGEVP_2, Image_2)
                        2->putString(IMAGEVP_4, Image_4)
                    }
                }
                4-> {
                    when(position){
                        0->putString(IMAGEVP_1, Image_1)
                        1->putString(IMAGEVP_2, Image_2)
                        2->putString(IMAGEVP_3, Image_3)
                        3->putString(IMAGEVP_4, Image_4)
                    }
                }
            }
        }
        return fragment
    }

    fun prover():Int{
        var i = 0
        if (Image_1!="0"&&Image_2=="0"&&Image_3=="0"&&Image_4=="0"){
            i = 1
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3=="0"&&Image_4=="0"){
            i = 1
        }else if (Image_1=="0"&&Image_2=="0"&&Image_3!="0"&&Image_4=="0"){
            i = 1
        }else if (Image_1=="0"&&Image_2=="0"&&Image_3=="0"&&Image_4!="0"){
            i = 1
        }else if (Image_1=="0"&&Image_2=="0"&&Image_3!="0"&&Image_4!="0"){
            i = 2
        }else if (Image_1!="0"&&Image_2=="0"&&Image_3!="0"&&Image_4=="0"){
            i = 2
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3!="0"&&Image_4=="0"){
            i = 2
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3=="0"&&Image_4=="0"){
            i = 2
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3=="0"&&Image_4!="0"){
            i = 3
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3!="0"&&Image_4=="0"){
            i = 3
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3!="0"&&Image_4!="0"){
            i = 3
        }else if (Image_1!="0"&&Image_2=="0"&&Image_3!="0"&&Image_4!="0"){
            i = 3
        }else if (Image_1!="0"&&Image_2!="0"&&Image_3!="0"&&Image_4!="0"){
            i = 4
        }else if (Image_1!="0"&&Image_2=="0"&&Image_3=="0"&&Image_4!="0"){
            i = 2
        }else if (Image_1=="0"&&Image_2!="0"&&Image_3=="0"&&Image_4!="0"){
            i = 2
        }
        return i
    }
}