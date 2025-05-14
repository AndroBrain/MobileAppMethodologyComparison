//
//  BudgetDataSource.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//
import Foundation

protocol BudgetDataSource {
    func getAll() -> [BudgetEntity]
    func addOrReplace(budget: BudgetModel)
    func delete(id: UUID)
}
