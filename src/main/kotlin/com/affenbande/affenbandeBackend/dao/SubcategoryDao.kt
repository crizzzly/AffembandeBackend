package com.affenbande.affenbandeBackend.dao

import com.affenbande.affenbandeBackend.model.Subcategory
import org.springframework.stereotype.Component

@Component
class SubcategoryDao: BaseDao<Subcategory>(Subcategory::class.java)