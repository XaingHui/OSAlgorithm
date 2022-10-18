import business.Work
import com.sun.jmx.remote.internal.ArrayQueue
import lab_one.FirstComeFirstServer
import lab_one.ShortJobFirst
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList
val works = ArrayList<Work>()
val read = Scanner(System.`in`)

class Window {
    fun startWidow() {
        choose()
    }

    fun choose() {
        println("----------------------------请根据提示选择你想要模拟的算法-----------------------")
        println("\t输入实验名称代表要选择的实验(如：实验一 1.先来先服务： (先来先服务))\t\t输入(退出) 立即退出")
        println("实验一\t\t 实验二\t\t 实验三\t\t\t 实验四\t\t\t 实验五")
        println("1.先来先服务\t 1.优先级调度\t 1.首次适应算法\t 1.最坏适应算法\t 1.先进先出页面置换算法")
        println("2.短作业优先\t")
        println("-----------------------------------------------------------------------------")


        while (true) {
            println("请输入:")
            val chooser = readLine()
            when (chooser) {
                "先来先服务" -> startFCFS(works)
                "短作业优先" -> startSJF(works)
                "退出" -> break
            }
        }
        println("----------------感谢使用----------------")
    }

    private fun startFCFS(works: ArrayList<Work>) {
        val ffcfs = FirstComeFirstServer.FCFS(works)
        ext(ffcfs, "先来先服务")
    }

    private fun startSJF(works: ArrayList<Work>) {
        val ssjf = ShortJobFirst.SJF(works)
        ext(ssjf, "短作业优先")
    }

    private fun ext(workext: ArrayList<Work>, chooser: String) {
        val Queue = ArrayQueue<Work>(5)
        Queue.addAll(workext)
        println("------------------------------------------${chooser}----------------------------------------------")
        println("作业名\t到达时间\t服务时间\t完成时间\t周转时间\t带权周转时间\t")
        Queue.forEach {
            it.PrintWork()
        }
        val format = DecimalFormat("0.##")
        println("平均周转时间:\t${format.format(works.last().AverRtime)}")
        println("平均带权周转时间:\t${format.format(works.last().AveDRtime)}")
    }
}