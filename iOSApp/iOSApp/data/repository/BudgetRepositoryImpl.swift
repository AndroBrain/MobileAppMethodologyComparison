//
//  BudgetRepositoryImpl.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//
import Foundation

class BudgetRepositoryImpl : BudgetRepository {
    let dataSource: BudgetDataSource
    
    init(dataSource: BudgetDataSource) {
        self.dataSource = dataSource
    }
    
    func getAll() -> [BudgetModel] {
        return dataSource.getAll().map {
            toModel(entity: $0)
        }
    }
    
    func addOrReplace(budget: BudgetModel) {
        dataSource.addOrReplace(budget: budget)
    }
    
    func delete(id: UUID) {
        dataSource.delete(id: id)
    }
    
    private func toModel(entity: BudgetEntity) -> BudgetModel {
        return BudgetModel(
            amount: entity.amount, content: entity.content!, id: entity.id!, isSpending: entity.isSpending
        )
    }
}
