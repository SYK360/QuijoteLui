package com.quijotelui.service

import com.quijotelui.model.Electronico
import com.quijotelui.repository.IElectronicoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ElectronicoServiceImpl : IElectronicoService {

    @Autowired
    lateinit var electronicoDao: IElectronicoDao

    override fun findAll(): MutableList<Electronico> {
        return electronicoDao.findAll()
    }

    override fun saveElectronico(electronico: Electronico) {
        electronicoDao.saveElectronico(electronico)
    }

}