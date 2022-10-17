import business.Work
import com.sun.jmx.remote.internal.ArrayQueue
import lab_one.FirstComeFirstServer
import lab_one.ShortJobFirst
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

val read = Scanner(System.`in`)

class Window {
    fun startWidow() {
        startFCFS()
        startSJF()
    }

    fun startFCFS() {
        val works = ArrayList<Work>()
        val fcfs = FirstComeFirstServer()
        val ffcfs = fcfs.FCFS(works)

        val fQueue = ArrayQueue<FirstComeFirstServer>(5)
        fQueue.let { fQueue.addAll(ffcfs) }

        println("------------------------------------------先来先服务----------------------------------------------")
        println("作业名\t到达时间\t服务时间\t完成时间\t周转时间\t带权周转时间\t")

        fQueue.forEach { it.PrintWork() }
        val format = DecimalFormat("0.##")
        val index = works.lastIndex
        println("平均周转时间:\t${format.format(works[index].AverRtime)}\t\t\t")
        println("平均带权周转时间:\t${format.format(works[index].AveDRtime)}\t\t\t")
    }

    fun startSJF() {
        val works = ArrayList<Work>()
        val sjf = ShortJobFirst()
        val ssjf = sjf.SJF(works)

        val sQueue = ArrayQueue<ShortJobFirst>(5)
        sQueue.let { sQueue.addAll(ssjf) }
        //sjf.SJF(works)
        println("------------------------------------------短作业优先----------------------------------------------")
        println("作业名\t到达时间\t服务时间\t完成时间\t周转时间\t带权周转时间\t")
        works.filter { (it is ShortJobFirst) }.forEach {
            it.PrintWork()
        }
        val format = DecimalFormat("0.##")
        val index = works.lastIndex
        println("平均周转时间:\t${format.format(works[index].AverRtime)}")
        println("平均带权周转时间:\t${format.format(works[index].AveDRtime)}")
    }
}