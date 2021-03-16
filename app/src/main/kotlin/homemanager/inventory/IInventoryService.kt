package homemanager.inventory

// Add dependency to user service to confirm if a user exists
interface IInventoryService {
    // Inventory service will be a facade for confirming if the userId is real
    // and store, search, modify entities for the user.

    // Move to a service called recipe service that will have custom logic for recipes
    //TODO: Function that answers if a user can prepare a recipe
    //TODO: Function that changes the stocks when a user consumes a recipe
}
