//
//  CoreBudgetdataSource.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//
import CoreData

class CoreBudgetdataSource : BudgetDataSource {
    let manager: CoreDataManager
    
    init (manager: CoreDataManager) {
        self.manager = manager
        manager.loadCoreData { _ in }
    }
    
    func getAll() -> [BudgetEntity] {
        let request: NSFetchRequest<BudgetEntity> = BudgetEntity.fetchRequest()
        
        do {
            return try manager.container.viewContext.fetch(request)
        } catch {
            print("Fetch failure: \(error)")
            return []
        }
    }
    
    func addOrReplace(budget: BudgetModel) {
        let request: NSFetchRequest<BudgetEntity> = BudgetEntity.fetchRequest()
        request.predicate = NSPredicate(format: "id == %@", budget.id.uuidString)
        let objects = try! manager.container.viewContext.fetch(request)
        let newBudget: BudgetEntity
        if objects.count > 0 {
            newBudget = objects.first!
        } else {
            newBudget = BudgetEntity(context: manager.container.viewContext)
        }
        newBudget.id = budget.id
        newBudget.content = budget.content
        newBudget.isSpending = budget.isSpending
        newBudget.amount = budget.amount
        
        save()
    }
    
    func delete(id: UUID) {
        let request: NSFetchRequest<BudgetEntity> = BudgetEntity.fetchRequest()
        request.predicate = NSPredicate(format: "id == %@", id.uuidString)
        let objects = try! manager.container.viewContext.fetch(request)
        for obj in objects {
            manager.container.viewContext.delete(obj)
        }
        save()
    }
    
    private func save() {
        do {
            try manager.container.viewContext.save()
        } catch {
            print("Error saving context: \(error)")
        }
    }
}
