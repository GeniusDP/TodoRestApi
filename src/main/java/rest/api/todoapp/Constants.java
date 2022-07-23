package rest.api.todoapp;

public class Constants {
    public static class TodoController {
        public static final String TAG = "Todo Controller";
        public static final String TAG_DESCRIPTION = "Controller that provides CRUD functionality for todo";
        public static final String GET_TODO_BY_ID_SUMMARY = "Get todo by id";
        public static final String GET_TODO_BY_ID_DESCRIPTION = "Retrieving a certain todo from a database by id";
        public static final String GET_PAGINATED_TODO_LIST_SUMMARY = "Get paginated todos";
        public static final String GET_PAGINATED_TODO_LIST_DESCRIPTION = "Get paginated todos using page number and page size";
        public static final String SAVE_TODO_ITEM_SUMMARY = "Save new todo";
        public static final String SAVE_TODO_ITEM_DESCRIPTION = "Save new todo using passed title and  body";
        public static final String UPDATE_TODO_BY_ID_SUMMARY = "Update or save todo by id";
        public static final String UPDATE_TODO_BY_ID_DESCRIPTION = "If todo with such id exists than updates its fields, otherways, if all " +
                "the required fields provided, saves a new todo. \"todoId\" parameter and \"todoId\" variable in response body" +
                "should be equal. Otherways error will be thrown.";
        public static final String DELETE_TODO_BY_ID_SUMMARY = "Delete todo by id";
        public static final String DELETE_TODO_BY_ID_DESCRIPTION = "If no such todo than message will be provided.";

    }

    public static class SwaggerDescriptions{
        public static final String TITLE = "title of todo";
        public static final String BODY = "description of todo";
        public static final String DONE_STATUS = "if todo is done or not yet";
        public static final String TODO_ID = "id of todo";
        public static final String ERROR_MESSAGE = "error message";
        public static final String CREATION_TIME = "todo creation time";
        public static final String LAST_UPDATE_TIME = "todo last update time";
        public static final String PAGE_NUMBER = "page number";
        public static final String PAGE_SIZE = "page size";
        public static final String FIELD_NAME = "name of field which constraint has been violated";
        public static final String ERROR_CAUSED_TIME = "time when error caused";
        public static final String TODO = "todo entity";
    }

    public static class Informer {
        public static final String HTTP_STATUS_OK = "200";
        public static final String HTTP_STATUS_NO_CONTENT = "204";
        public static final String HTTP_STATUS_CREATED = "201";
    }
}
