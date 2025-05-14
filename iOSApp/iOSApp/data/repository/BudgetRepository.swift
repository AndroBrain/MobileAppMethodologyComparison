//
//  BudgetRepository.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//
import Foundation

protocol BudgetRepository {
    func getAll() -> [BudgetModel]
    func addOrReplace(budget: BudgetModel)
    func delete(id: UUID)
}
