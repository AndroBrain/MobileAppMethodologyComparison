//
//  CoreDataManager.swift
//  iOSApp
//
//  Created by Michal Ankiersztajn on 14/05/2025.
//

import CoreData

class CoreDataManager {
    
    let container: NSPersistentContainer

    init() {
        container = NSPersistentContainer(name: "BudgetContainer")
    }

    func loadCoreData(completion: @escaping (Bool) -> Void) {
        container.loadPersistentStores { description, error in
            DispatchQueue.main.async {
                if let error = error {
                    print("Core Data loading error: \(error.localizedDescription)")
                    completion(false)
                } else {
                    completion(true)
                }
            }
        }
        container.viewContext.mergePolicy = NSMergeByPropertyObjectTrumpMergePolicy
    }
}
