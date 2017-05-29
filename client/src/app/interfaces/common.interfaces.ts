export interface SearchBody {
    queryText: string;
    sortingAlgorithm: string;
}

export interface SearchBodyWithDecisionSupport {
    query: QueryWithDecisionSupport;
    sortingAlgorithm: string;
}

export interface QueryWithDecisionSupport {
    queryText: string,
    weights: Object,
    positiveArticles: Object[],
    negativeArticles: Object[]
}