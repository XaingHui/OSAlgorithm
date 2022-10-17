package business

interface Calculate {
    fun Calculate(works: ArrayList<Work>): ArrayList<Work> {
        var sum1 = 0
        var sum2 = 0.0
        for (i in 0..works.size - 1) {
            if (i == 0)
                works[0].Ftime = works[0].Atime + works[0].Stime
            else if (works[i].Atime > works[i - 1].Ftime)
                works[i].Ftime = works[i].Atime + works[i].Stime;
            else
                works[i].Ftime = works[i - 1].Ftime + works[i].Stime;
        }
        works.run {
            forEach {
                it.Rtime = it.Ftime - it.Atime
                it.DRtime = (it.Rtime / it.Stime).toDouble()
                sum1 += it.Rtime
                it.AverRtime = (sum1 / works.size).toDouble()
                sum2 += it.DRtime
                it.AveDRtime = sum2 / works.size
            }
        }
        return works
    }

}



